package com.example.back_app_rjf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.back_app_rjf.model.FormSchema;
import com.example.back_app_rjf.repository.FormSchemaRepository;

@Service
public class FormSchemaService {

    private final FormSchemaRepository formSchemaRepository;

    public FormSchemaService(FormSchemaRepository formSchemaRepository) {
        this.formSchemaRepository = formSchemaRepository;
    }

    @Transactional(readOnly = true)
    public List<FormSchema> getFormSchemaAll() {
        // mongodbのデータベース:formdbのcollection:formschemaから全件取得
        // 取得したデータをList<FormSchema>に変換して返す
        return formSchemaRepository.findAll();
    }

    // IDでFormSchemaを取得するメソッド
    @Transactional(readOnly = true)
    public FormSchema getFormSchemaById(String id) {
        return formSchemaRepository.findById(id).orElse(null);
    }
}
