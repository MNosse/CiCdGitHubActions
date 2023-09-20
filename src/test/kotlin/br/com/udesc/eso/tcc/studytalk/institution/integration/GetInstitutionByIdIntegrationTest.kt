package br.com.udesc.eso.tcc.studytalk.institution.integration

import br.com.udesc.eso.tcc.studytalk.entity.administrator.exception.AdministratorNotFoundException
import br.com.udesc.eso.tcc.studytalk.entity.institution.exception.InstitutionNotFoundException
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.repository.administrator.AdministratorRepository
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.repository.institution.InstitutionRepository
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.administrator.AdministratorSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.institution.InstitutionSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.institution.controller.GetInstitutionByIdController
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GetInstitutionByIdIntegrationTest @Autowired constructor(
    private val administratorRepository: AdministratorRepository,
    private val institutionRepository: InstitutionRepository,
    private val getInstitutionByIdController: GetInstitutionByIdController
) {
    val administratorUid = "VcZSfuTj8ENjztIccfjbK2KRbHf2"
    val institution1Name = "Instituição 1"
    val institution2Name = "Instituição 2"
    val participantUid = "VoZSfuTj8ENjztIccfjbK2KRbHf1"

    @BeforeEach
    fun setup(testInfo: TestInfo) {
        administratorRepository.save(AdministratorSchema(uid = administratorUid))
        institutionRepository.save(InstitutionSchema(name = institution1Name))
        institutionRepository.save(InstitutionSchema(name = institution2Name))
    }

    @Test
    fun withValidValues1() {
        assertDoesNotThrow {
            val response = getInstitutionByIdController.getInstitutionById(
                requestingUid = administratorUid,
                id = 1L
            )
            assert(response.id == 1L && response.name == institution1Name && response.registrationCode.isNotBlank())
        }
    }

    @Test
    fun withInvalidId() {
        assertThrows<InstitutionNotFoundException> {
            getInstitutionByIdController.getInstitutionById(
                requestingUid = administratorUid,
                id = 3L
            )
        }
    }

    @Test
    fun withNonExistentAdministrator() {
        assertThrows<AdministratorNotFoundException> {
            getInstitutionByIdController.getInstitutionById(
                requestingUid = administratorUid + "a",
                id = 2L
            )
        }
    }
}