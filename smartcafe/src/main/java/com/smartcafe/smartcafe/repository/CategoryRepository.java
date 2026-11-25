package com.smartcafe.smartcafe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.Category;
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
