package com.example.turing_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class DoubleArchTuringServiceTest {
    private static DoubleArchTuringService doubleArchTuringService;

    @BeforeAll
    public static void init() {
        DocumentService documentService = Mockito.mock(DocumentService.class);
        Mockito.when(documentService.checkExistence("ПР по СЛ ЕЦП2 2.0 v.1.22 ИТОГ от 25122023[2].docx")).thenReturn(true);
        doubleArchTuringService = new DoubleArchTuringService(documentService);
    }
    @Test
    public void shouldFindMatches() {
        String request = "Есть ли документ //ПР по СЛ ЕЦП2 2.0 v.1.22 ИТОГ от 25122023[2].docx// ";
        assert(doubleArchTuringService.hasDocumentName(request));
    }

    @Test
    public void twoDocuments() {
        String request = " //Документ// Есть ли документ //ПР по СЛ ЕЦП2 2.0 v.1.22 ИТОГ от 25122023[2].docx// ";
        assert(doubleArchTuringService.hasDocumentName(request));
    }
}