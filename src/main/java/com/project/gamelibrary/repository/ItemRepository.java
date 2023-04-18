package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository  extends CrudRepository<Item,Long> {
}
