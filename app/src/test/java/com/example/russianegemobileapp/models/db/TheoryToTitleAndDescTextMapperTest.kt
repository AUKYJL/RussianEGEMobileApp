package com.example.russianegemobileapp.models.db

import com.example.russianegemobileapp.models.db.local.Theory
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.mock

val TEXT_FOR_TEST = """
            <t>Заголовок1
            
            Какой-то текст
                  для описания
            задачки все-такое
            <t>Еще заголовок 2
            Еще текст
        """.trimIndent()

internal class TextToTitleAndDescTextMapperTest {

    val theory = mock<Theory>()

    @Test
    fun `should return strings with only titles`(){
        Mockito.`when`(theory.theoryText).thenReturn(TEXT_FOR_TEST)
        val  textToTitleAndDescTextMapper = TextToTitleAndDescTextMapper()
        val actual = textToTitleAndDescTextMapper.execute(theory).get(0)
        val excepted = arrayListOf("Заголовок1","Еще заголовок 2")
        Assertions.assertEquals(excepted,actual)
    }
    @Test
    fun `should return strings with only descs`(){
        Mockito.`when`(theory.theoryText).thenReturn(TEXT_FOR_TEST)
        val  textToTitleAndDescTextMapper = TextToTitleAndDescTextMapper()
        val actual = textToTitleAndDescTextMapper.execute(theory).get(1)
        val excepted = arrayListOf("Какой-то текст для описания задачки все-такое","Еще текст")
        Assertions.assertEquals(excepted,actual)
    }

    @Test
    fun `should return 2 arrays`() {
        Mockito.`when`(theory.theoryText).thenReturn(TEXT_FOR_TEST)
        val  textToTitleAndDescTextMapper = TextToTitleAndDescTextMapper()
        val actual = textToTitleAndDescTextMapper.execute(theory)
        val excepted = arrayListOf(arrayListOf("Заголовок1","Еще заголовок 2"),arrayListOf("Какой-то текст для описания задачки все-такое","Еще текст"))
        Assertions.assertEquals(excepted,actual)
    }
}