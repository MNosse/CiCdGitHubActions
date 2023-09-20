//package br.com.udesc.eso.tcc.studytalk.participant
//
//import br.com.udesc.eso.tcc.studytalk.core.enums.Privilege
//import br.com.udesc.eso.tcc.studytalk.entity.administrator.exception.AdministratorNotFoundException
//import br.com.udesc.eso.tcc.studytalk.entity.institution.exception.InstitutionNotFoundException
//import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantNotFoundException
//import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantWithoutPrivilegeException
//import br.com.udesc.eso.tcc.studytalk.infrastructure.administrator.controller.CreateAdministratorController
//import br.com.udesc.eso.tcc.studytalk.infrastructure.institution.controller.*
//import br.com.udesc.eso.tcc.studytalk.infrastructure.participant.controller.*
//import jakarta.validation.ConstraintViolationException
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.junit.jupiter.SpringExtension
//
//@ExtendWith(SpringExtension::class)
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class ParticipantUnitTest @Autowired constructor(
//    private val answerAQuestionController: AnswerAQuestionController,
//    private val changeAnAnswerLikeStatusController: ChangeAnAnswerLikeStatusController,
//    private val changeAQuestionFavoriteStatusController: ChangeAQuestionFavoriteStatusController,
//    private val createAdministratorController: CreateAdministratorController,
//    private val createInstitutionController: CreateInstitutionController,
//    private val createParticipantController: CreateParticipantController,
//    private val deleteParticipantController: DeleteParticipantController,
//    private val doAQuestionController: DoAQuestionController,
//    private val getAllParticipantsByInstitutionIdController: GetAllParticipantsByInstitutionIdController,
//    private val getAllParticipantsController: GetAllParticipantsController,
//    private val getInstitutionByIdController: GetInstitutionByIdController,
//    private val getParticipantByUidController: GetParticipantByUidController,
//    private val updateParticipantController: UpdateParticipantController,
//    private val updateParticipantPrivilegeController: UpdateParticipantPrivilegeController
//) {
//    var administratorUid = "VcZSfuTj8ENjztIccfjbK2KRbHf2"
//    var institutionId = 0L
//    var registrationCode = ""
//
//    @BeforeAll
//    fun setup(): Unit {
//        assertDoesNotThrow {
//            createAdministratorController.createAdministrator(
//                CreateAdministratorController.Request(
//                    administratorUid
//                )
//            )
//            createInstitutionController.createInstitution(
//                CreateInstitutionController.Request(
//                    administratorUid = administratorUid,
//                    name = "Instituição 1"
//                )
//            )
//            val institution = getInstitutionByIdController.getInstitutionById(1L)
//            institutionId =  institution.id
//            registrationCode = institution.registrationCode
//        }
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
//    fun createParticipantWithInvalidRegistrationCode() {
//        assertThrows<InstitutionNotFoundException> {
//            createParticipantController.createParticipant(
//                CreateParticipantController.Request(
//                    registrationCode = " ",
//                    uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = "Mateus Coelho",
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(5)
//    fun getParticipantWithValidUid() {
//        val response = getParticipantByUidController.getParticipantByUid(uid = "VoZSfuTj8ENjztIccfjbK2KRbHf1")
//        assert(response.id == 1L)
//        assert(response.name == "Mateus Nosse")
//        assert(response.privilege == Privilege.DEFAULT)
//    }
//
//    @Test
//    @Order(6)
//    fun getParticipantWithInvalidUid() {
//        assertThrows<ParticipantNotFoundException> {
//            getParticipantByUidController.getParticipantByUid(uid = "AADEiZohc1SwsBgP2qKQJKE8p8o2")
//        }
//    }
//
//    @Test
//    @Order(7)
//    fun getParticipants() {
//        val response = getAllParticipantsController.getAllParticipants(administratorUid)
//        assert(response.participants.isNotEmpty())
//    }
//
//    @Test
//    @Order(8)
//    fun getParticipantsWithInvalidAdministrator() {
//        assertThrows<AdministratorNotFoundException> {
//            getAllParticipantsController.getAllParticipants(administratorUid.plus("a"))
//        }
//    }
//
//    @Test
//    @Order(9)
//    fun updateParticipantWithValidValues() {
//        assertDoesNotThrow {
//            updateParticipantController.updateParticipant(
//                participantToBeUpdatedUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                UpdateParticipantController.Request(
//                    requestingParticipantUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = "Mateus Coelho Nosse"
//                )
//            )
//        }
//        val response = getParticipantByUidController.getParticipantByUid(uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1")
//        assert(response.name == "Mateus Coelho Nosse")
//    }
//
//    @Test
//    @Order(10)
//    fun updateParticipantWithBlankName() {
//        assertThrows<ConstraintViolationException> {
//            updateParticipantController.updateParticipant(
//                participantToBeUpdatedUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                UpdateParticipantController.Request(
//                    requestingParticipantUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = " "
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(11)
//    fun updateParticipantWithOverflowedName() {
//        assertThrows<ConstraintViolationException> {
//            updateParticipantController.updateParticipant(
//                participantToBeUpdatedUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                UpdateParticipantController.Request(
//                    requestingParticipantUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1",
//                    name = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy"
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(12)
//    fun updateParticipantWithInvalidUid() {
//        assertThrows<ParticipantNotFoundException> {
//            updateParticipantController.updateParticipant(
//                participantToBeUpdatedUid = "AADEiZohc1SwsBgP2qKQJKE8p8o2",
//                UpdateParticipantController.Request(
//                    requestingParticipantUid = "AADEiZohc1SwsBgP2qKQJKE8p8o2",
//                    name = "Mateus Coelho Nosse"
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(13)
//    fun updateParticipantPrivilegeWithValidAdministrator() {
//        assertDoesNotThrow {
//            updateParticipantPrivilegeController.updateParticipantPrivilege(
//                participantToBeUpdatedUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1" ,
//                UpdateParticipantPrivilegeController.Request(
//                    requestingUid = administratorUid,
//                    isAdministrator = true,
//                    privilege = Privilege.PRINCIPAL
//                )
//            )
//        }
//    }
//
//    @Test
//    @Order(14)
//    fun updateParticipantPrivilegeWithInvalidAdministrator() {
//        assertThrows<AdministratorNotFoundException> {
//            updateParticipantPrivilegeController.updateParticipantPrivilege(
//                participantToBeUpdatedUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1" ,
//                UpdateParticipantPrivilegeController.Request(
//                    requestingUid = administratorUid.plus("a"),
//                    isAdministrator = true,
//                    privilege = Privilege.PRINCIPAL
//                )
//            )
//        }
//    }
//
////    @Test
////    @Order(15)
////    fun updateParticipantPrivilegeWithInvalidParticiantPrivilege() {
////        assertThrows<ParticipantWithoutPrivilegeException> {
////            updateParticipantPrivilegeController.updateParticipantPrivilege(
////                participantToBeUpdatedUid = "AADEiZohc1SwsBgP2qKQJKE8p8o1" ,
////                UpdateParticipantPrivilegeController.Request(
////                    requestingUid = "VoZSfuTj8ENjztIccfjbK2KRbHf1",
////                    isAdministrator = false,
////                    privilege = Privilege.PRINCIPAL
////                )
////            )
////        }
////    }
//
//
////
////    @Test
////    @Order(11)
////    fun deleteParticipant() {
////        assertDoesNotThrow {
////            deleteParticipantController.deleteParticipantByUid(uid = "AADEiZohc1SwsBgP2qKQJKE8p8o1")
////        }
////    }
//}
