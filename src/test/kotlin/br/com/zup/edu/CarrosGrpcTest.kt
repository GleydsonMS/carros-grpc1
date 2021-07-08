package br.com.zup.edu
import br.com.zup.edu.carros.Carro
import br.com.zup.edu.carros.CarroRepository
import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject


@MicronautTest(
    rollback = false,
    transactionMode = TransactionMode.SINGLE_TRANSACTION,
    transactional = false
)
class CarrosGrpcTest {

    @Inject
    lateinit var repository: CarroRepository

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @AfterEach
    fun cleanUp() {
        repository.deleteAll()
    }

    @Test
    fun `deve inserir um novo carro`() {

        repository.save(Carro(modelo = "Gol", placa = "HPX-1234"))

        assertEquals(1, repository.count())
    }

    @Test
    fun `deve encontrar carro por placa`() {

        repository.save(Carro(modelo = "Fox", placa = "GOL-5432"))

        val encontrado = repository.existsByPlaca("GOL-5432")

        assertTrue(encontrado)
    }
}
