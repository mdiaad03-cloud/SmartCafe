package com.smartcafe.smartcafe;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.smartcafe.smartcafe.model.CafeTable;
import com.smartcafe.smartcafe.model.Category;
import com.smartcafe.smartcafe.model.MenuItem;
import com.smartcafe.smartcafe.repository.CafeTableRepository;
import com.smartcafe.smartcafe.repository.CategoryRepository;
import com.smartcafe.smartcafe.repository.MenuItemRepository;
@SpringBootTest
@TestPropertySource(properties={
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class SmartCafeFlowIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CafeTableRepository cafeTableRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    private MockMvc mockMvc;
    @BeforeEach
    void setup(){
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    void fullFlow_startSession_addCartItems_createOrder_clearsCart() throws Exception{
        CafeTable table=new CafeTable();
        table.setNumber(1);
        table.setSeats(4);
        table.setStatus("AVAILABLE");
        table=cafeTableRepository.save(table);
        Category category=new Category();
        category.setName("Drinks");
        category=categoryRepository.save(category);
        MenuItem latte=new MenuItem();
        latte.setName("Latte");
        latte.setPrice(45.5);
        latte.setAvailable(true);
        latte.setCategory(category);
        latte=menuItemRepository.save(latte);
        MenuItem tea=new MenuItem();
        tea.setName("Tea");
        tea.setPrice(20.0);
        tea.setAvailable(true);
        tea.setCategory(category);
        tea=menuItemRepository.save(tea);
        String sessionResponse=mockMvc.perform(post("/api/session/start/"+table.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andReturn().getResponse().getContentAsString();
        String sessionId=sessionResponse.replaceAll(".*\"id\":(\\d+).*", "$1");
        mockMvc.perform(post("/api/cart/add/"+sessionId+"/"+latte.getId())
                .param("quantity", "2")
                .param("note", "no sugar")
                .param("addedBy", "Ahmed"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.quantity").value(2));
        mockMvc.perform(post("/api/cart/add/"+sessionId+"/"+tea.getId())
                .param("quantity", "1")
                .param("addedBy", "Sara"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.quantity").value(1));
        mockMvc.perform(get("/api/cart/"+sessionId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
        mockMvc.perform(post("/api/orders/create/"+table.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("PENDING"))
            .andExpect(jsonPath("$.total").value(111.0));
        mockMvc.perform(get("/api/cart/"+sessionId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }
}