package com.project.gamelibrary.service;

import com.project.gamelibrary.domain.BoardCategory;
import com.project.gamelibrary.repository.BoardCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    public void saveBoardCatogry(BoardCategory boardCategory) {boardCategoryRepository.save(boardCategory);}

    public List<BoardCategory> findAll() { return boardCategoryRepository.findAll();}

    public BoardCategory findOne(Long boardCategoryId) {return boardCategoryRepository.findOne(boardCategoryId);}
}
