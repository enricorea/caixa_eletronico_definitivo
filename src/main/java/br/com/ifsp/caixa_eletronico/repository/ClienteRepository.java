package br.com.ifsp.caixa_eletronico.repository;

import br.com.ifsp.caixa_eletronico.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

//repository -> acessar entidades do banco de dados
//passa a entidade cliente e o tipo do ID
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    Cliente findByNumeroContaAndPin(String numeroConta, String pin);

    Cliente findClienteByNumeroConta(String numerConta);

    //@Query(value="SELECT Saldo FROM Cliente", nativeQuery=true)
    //List<Cliente> listar_saldo();

    //Extendendo JpaRepository passando a entidade Cliente e o tipo do id (Long) da entidade cliente
    //é possível ter acesso a alguns métodos já implementados
    //ao acessar o JpaRepository, é possível verificar que ele extende PagingAndSortingRepository
    //que extende CrudRepository, que possui as implementações: save, findById, findAll e delete
    //portanto através dessa interface conseguimos criar um cliente, atualizar um cliente,
    //consultar um cliente e deletar um cliente

}
