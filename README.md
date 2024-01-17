# ✨ Resumo Geral
Uma API para gerar senhas gerais, que podem ter seu **nível de segurança**, **tamanho** e **palavras-chave** personalizadas.

# 🤔 O Que Preciso Instalar?
Para rodar o projeto na sua máquina, é necessário ter instalado:
- [Java SDK](https://www.oracle.com/br/java/technologies/downloads/) (v.21.0.2).
- [Apache Maven](https://maven.apache.org/download.cgi) (v.3.9.6).

E obviamente, é necessário configurar as variáveis de ambiente dessas duas tecnologias.

# ✈️ Chamar a API:
**ROTA**: /password

**CORPO DA REQUISIÇÃO**:
```json
{
  "securityLevel": 2,
  "amountCharacters": 25,
  "keyword": "bola"
}
```
Sendo:
- **securityLevel**: de 0 a 3.
  - 0 -> Só números.
  - 1 -> Números e letras minúsculas.
  - 2 -> Números, letras minúsculas e maiúsculas.
  - 3 -> Números, letras minúsculas, maiúsculas e caracteres especiais.
- **amountCharacters**: de no mínimo 8.
- **keyword**: uma palavra que você quer que a senha contenha.
