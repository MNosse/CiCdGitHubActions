//package br.com.udesc.eso.tcc.studytalk.participant
//
//import org.junit.jupiter.api.Order
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.test.context.event.annotation.BeforeTestClass
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//
//@ExtendWith(SpringExtension::class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class ParticipantIntegrationTest @Autowired constructor(
//    private val mockMvc: MockMvc
//) {
//    lateinit var registrationCode: String
//
//    @BeforeTestClass
//    fun createInstitution() {
//        val requestBody1 = "{\"name\": \"Instituição 1\"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/studytalk/api/institutions/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody1)
//        )
//
//        registrationCode = mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/studytalk/api/institutions/1")
//        ).andReturn().response.contentAsString
//    }
//
//
//    @Test
//    @Order(1)
//    fun createParticipantWithValidValues() {
//        val requestBody1 =
//            "{\"registrationCode\": \"$registrationCode\", \"uid\": \"VoZSfuTj8ENjztIccfjbK2KRbHf1\", \"name\": \"Mateus Nosse\"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/studytalk/api/participants/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody1)
//        ).andExpect(MockMvcResultMatchers.status().isOk)
//
//        val requestBody2 =
//            "{\"registrationCode\": \"$registrationCode\", \"uid\": \"AADEiZohc1SwsBgP2qKQJKE8p8o1\", \"name\": \"Mateus Coelho\"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/studytalk/api/participants/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody2)
//        ).andExpect(MockMvcResultMatchers.status().isOk)
//    }
//
//    @Test
//    @Order(2)
//    fun createParticipantWithBlankValues() {
//        val requestBody = "{\"registrationCode\": \" \", \"uid\": \" \", \"name\": \" \"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/studytalk/api/participants/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody)
//        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
//            .andExpect(
//                MockMvcResultMatchers.content().json("{\"registrationCode\": \"Código de cadastro inválido\", \"uid\": \"O uid deve conter ao menos um caractere\", \"name\":\"O nome deve conter ao menos um caractere.\"}")
//            )
//    }
//
//    @Test
//    @Order(3)
//    fun createParticipantWithOverflowedName() {
//        val requestBody =
//            "{\"registrationCode\": \"$registrationCode\", \"uid\": \"VoZSfuTj8ENjztIccfjbK2KRbHf1\", \"name\": \"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy\"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/studytalk/api/participants/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody)
//        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
//            .andExpect(
//                MockMvcResultMatchers.content().json("{\"name\":\"O nome n\u00E3o pode ultrapassar 128 caracteres.\"}")
//            )
//    }
//
//    @Test
//    @Order(4)
//    fun getParticipantWithValidUid() {
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/studytalk/api/participants/VoZSfuTj8ENjztIccfjbK2KRbHf1")
//        ).andExpect(MockMvcResultMatchers.status().isFound)
//            .andExpect(
//                MockMvcResultMatchers.content()
//                    .json("{\"id\":1,\"uid\":VoZSfuTj8ENjztIccfjbK2KRbHf1\",\"name\":\"Mateus Nosse\",\"privilege\":\"DEFAULT\"," +
//                            "\"institution\":\"{\"id\":1,\"name\":\"Instituição 1\"}")
//            )
//    }
//
//    @Test
//    @Order(5)
//    fun getParticipantWithInvalidUid() {
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/studytalk/api/participants/VoZSfuTj8ENjztIccfjbK2KRbHf2")
//        ).andExpect(MockMvcResultMatchers.status().isNotFound)
//    }
//
//    @Test
//    @Order(6)
//    fun getParticipants() {
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/studytalk/api/participants/")
//        ).andExpect(MockMvcResultMatchers.status().isFound)
//            .andExpect(
//                MockMvcResultMatchers.content()
//                    .json("{\"participants\":[" +
//                            "{\"id\":1,\"uid\":VoZSfuTj8ENjztIccfjbK2KRbHf1\",\"name\":\"Mateus Nosse\",\"privilege\":\"DEFAULT\"," +
//                            "\"institution\":\"{\"id\":1,\"name\":\"Instituição 1\"}" +
//                            "{\"id\":2,\"uid\":AADEiZohc1SwsBgP2qKQJKE8p8o1\",\"name\":\"Mateus Coelho\",\"privilege\":\"DEFAULT\"," +
//                            "\"institution\":\"{\"id\":1,\"name\":\"Instituição 1\"}" +
//                            "]}")
//            )
//    }
//
//    @Test
//    @Order(7)
//    fun updateParticipantWithValidValues() {
//        val requestBody =  "{\"name\": \"Mateus Coelho Nosse\"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .put("/studytalk/api/participants/AADEiZohc1SwsBgP2qKQJKE8p8o1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody)
//        ).andExpect(MockMvcResultMatchers.status().isOk)
//
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/studytalk/api/participants/AADEiZohc1SwsBgP2qKQJKE8p8o1")
//        ).andExpect(MockMvcResultMatchers.status().isFound)
//            .andExpect(
//                MockMvcResultMatchers.content()
//                    .json("{\"id\":2,\"uid\":AADEiZohc1SwsBgP2qKQJKE8p8o1\",\"name\":\"Mateus Coelho Nosse\",\"privilege\":\"DEFAULT\"," +
//                            "\"institution\":\"{\"id\":1,\"name\":\"Instituição 1\"}")
//            )
//    }
//
//    @Test
//    @Order(8)
//    fun updateParticipantWithBlankName() {
//        val requestBody = "{\"name\": \" \"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .put("/studytalk/api/participants/AADEiZohc1SwsBgP2qKQJKE8p8o1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody)
//        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
//            .andExpect(
//                MockMvcResultMatchers.content().json("{\"name\":\"O nome deve conter ao menos um caractere.\"}")
//            )
//    }
//
//    @Test
//    @Order(9)
//    fun updateParticipantWithOverflowedName() {
//        val requestBody =
//            "{\"name\": \"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy\"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .put("/studytalk/api/participants/AADEiZohc1SwsBgP2qKQJKE8p8o1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody)
//        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
//            .andExpect(
//                MockMvcResultMatchers.content().json("{\"name\":\"O nome n\u00E3o pode ultrapassar 128 caracteres.\"}")
//            )
//    }
//
//    @Test
//    @Order(10)
//    fun updateParticipantWithInvalidUid() {
//        val requestBody =  "{\"name\": \"Mateus Coelho Nosse\"}"
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .put("/studytalk/api/participants/AADEiZohc1SwsBgP2qKQJKE8p8o2")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody)
//        ).andExpect(MockMvcResultMatchers.status().isNotFound)
//    }
//
//	@Test
//	@Order(11)
//	fun deleteInstitution() {
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .delete("/studytalk/api/participants/AADEiZohc1SwsBgP2qKQJKE8p8o1")
//        ).andExpect(MockMvcResultMatchers.status().isOk)
//	}
//}
