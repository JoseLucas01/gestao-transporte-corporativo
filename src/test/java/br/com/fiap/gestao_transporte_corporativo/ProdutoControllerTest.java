// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package br.com.fiap.gestao_transporte_corporativo;

import br.com.fiap.gestao_transporte_corporativo.dto.UsuarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.model.Usuario;
import br.com.fiap.gestao_transporte_corporativo.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class ProdutoControllerTest {
   @Autowired
   private MockMvc mockMvc;
   @MockBean
   private UsuarioService service;
   private UsuarioCadastroDto produtoCreateDto;

   public ProdutoControllerTest() {
   }

   @BeforeEach
   public void setup() {
      this.produtoCreateDto = new UsuarioCadastroDto(null, null, null, null, null);
      //this.produtoCreateDto.setNome("Produto novo");
   }

   @DisplayName("test create produto")
   @Test
   void testGivenNewProduto_whenCreate_thenSavedProduto() throws Exception {
      //BDDMockito.given(this.service.saveOrUpdate((Produto)BDDMockito.any(Produto.class))).willAnswer((invocation) -> {
      //   return invocation.getArguments()[0];
      //});
      String body = "{\"nome\":\"Maçã\"}";
      ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos", new Object[0]).contentType(MediaType.APPLICATION_JSON).content(body));
      response.andExpect(MockMvcResultMatchers.status().isOk());
   }

   @DisplayName("test fail")
   @Test
   void testGivenNewProduto_whenCreate_thenFail() throws Exception {
   }
}
