package com.example.postgresql.batch.demo.service;

import com.example.postgresql.batch.demo.domain.Book;
import com.example.postgresql.batch.demo.repository.BookRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondTasklet implements Tasklet {

    @Autowired
    BookRepository bookRepository1;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Iniciando mi  segundo job Nelson con Postgresql");
        System.out.println("------");
        System.out.println("Consultando ultimo registro");
        Book book1 = bookRepository1.findTopByOrderByIdDesc();
        if(book1.getAuthor().equals("J. R. Tolkien")){
            System.out.println("El titulo es " + book1.getTitle());
        }
        else{
            System.out.println("El autor es " + book1.getAuthor());
        }
        System.out.println("------");
        System.out.println("------");
        System.out.println("Finalizando mi segundo job Nelson  con Postgresql");
        return RepeatStatus.FINISHED;
    }
}
