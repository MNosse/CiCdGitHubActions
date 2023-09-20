package br.com.udesc.eso.tcc.studytalk.report

//import br.com.udesc.eso.tcc.studytalk.core.enums.Privilege
//import br.com.udesc.eso.tcc.studytalk.entity.answer.model.Answer
//import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantNotFoundException
//import br.com.udesc.eso.tcc.studytalk.entity.question.model.Question
//import br.com.udesc.eso.tcc.studytalk.infrastructure.institution.controller.CreateInstitutionController
//import br.com.udesc.eso.tcc.studytalk.infrastructure.institution.controller.GetInstitutionByIdController
//import jakarta.validation.ConstraintViolationException
//import org.junit.jupiter.api.Order
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertDoesNotThrow
//import org.junit.jupiter.api.assertThrows
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.test.context.event.annotation.BeforeTestClass
//
//class ReportUnitTest @Autowired constructor(
//    private val createReportController: CreateReportController,
//    private val getAllReportsController: GetAllReportsController,
//    private val getReportByIdController: GetReportByIdController,
//    private val approveReportController: ApproveReportController,
//    private val reproveReportController: ReproveReportController
//) {
//    lateinit var question: Question
//    lateinit var answer: Answer
//
//    @BeforeTestClass
//    fun createQuestionAndAnswer() {
//        createInstitutionController.createInstitution(CreateInstitutionController.Request("Instituição 1"))
//        registrationCode = getInstitutionByIdController.getInstitutionById(1L).registrationCode
//    }
//
//    @Test
//    @Order(1)
//    fun createParticipantWithValidValues() {
//        assertDoesNotThrow {
//            createParticipantController.createParticipant(
//                CreateParticipantController.Request(
//                    registrationCode = registrationCode,
//                    uid = "VoZSfuTj8ENjztIccfjbK2KRbHf1",
//                    name = "Mateus Nosse",
//                )
//            )
//            createParticipantController.createParticipant(
//                CreateParticipantController.Request(
//                    registrationCode = registrationCode,
//                    uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = "Mateus Coelho",
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(2)
//    fun createParticipantWithBlankValues() {
//        assertThrows<ConstraintViolationException> {
//            createParticipantController.createParticipant(
//                CreateParticipantController.Request(
//                    registrationCode = registrationCode,
//                    uid = " ",
//                    name = " "
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(3)
//    fun createParticipantWithOverflowedName() {
//        assertThrows<ConstraintViolationException> {
//            createParticipantController.createParticipant(
//                CreateParticipantController.Request(
//                    registrationCode = registrationCode,
//                    uid = "VoZSfuTj8ENjztIccfjbK2KRbHf1",
//                    name = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy"
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(4)
//    fun getParticipantWithValidUid() {
//        val response = getParticipantByUidController.getParticipantByUid(uid = "VoZSfuTj8ENjztIccfjbK2KRbHf1")
//        assert(response.id == 1L)
//        assert(response.name == "Mateus Nosse")
//        assert(response.privilege == Privilege.DEFAULT)
//    }
//
//    @Test
//    @Order(5)
//    fun getParticipantWithInvalidUid() {
//        assertThrows<ParticipantNotFoundException> {
//            getParticipantByUidController.getParticipantByUid(uid = "AADEiZohc1SwsBgP2qKQJKE8p8o2")
//        }
//    }
//
//    @Test
//    @Order(6)
//    fun getParticipants() {
//        val response = getAllParticipantsController.getAllParticipants()
//        assert(response.participants.isNotEmpty())
//    }
//
//    @Test
//    @Order(7)
//    fun updateParticipantWithValidValues() {
//        assertDoesNotThrow {
//            updateParticipantController.updateParticipant(
//                UpdateParticipantController.Request(
//                    uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = "Mateus Coelho Nosse"
//                )
//            )
//        }
//        val response = getParticipantByUidController.getParticipantByUid(uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1")
//        assert(response.name == "Mateus Coelho Nosse")
//    }
//
//    @Test
//    @Order(8)
//    fun updateParticipantWithBlankName() {
//        assertThrows<ConstraintViolationException> {
//            updateParticipantController.updateParticipant(
//                UpdateParticipantController.Request(
//                    uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = " "
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(9)
//    fun updateParticipantWithOverflowedName() {
//        assertThrows<ConstraintViolationException> {
//            updateParticipantController.updateParticipant(
//                UpdateParticipantController.Request(
//                    uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy"
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(10)
//    fun updateParticipantWithInvalidUid() {
//        assertThrows<ParticipantNotFoundException> {
//            updateParticipantController.updateParticipant(
//                UpdateParticipantController.Request(
//                    uid = "AADEiZohc1SwsBgP2qKQJKE8p8o2",
//                    name = "Mateus Coelho Nosse"
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(11)
//    fun deleteParticipant() {
//        assertDoesNotThrow {
//            deleteParticipantController.deleteParticipantByUid(uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1")
//        }
//    }
//}
