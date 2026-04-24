package br.com.senac.carros.services;

import br.com.senac.carros.dtos.CarrosFiltroDto;
import br.com.senac.carros.dtos.CarrosRequestDto;
import br.com.senac.carros.entidades.Carros;
import br.com.senac.carros.repositorios.CarrosRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrosService {
    private CarrosRepositorio carrosRepositorio;

    public CarrosService(CarrosRepositorio carrosRepositorio) {
        this.carrosRepositorio = carrosRepositorio;
    }
    public List<Carros> listar(CarrosFiltroDto filtro) {
        if(filtro.getModelo() != null) {
            return carrosRepositorio
                    .findByModelo(filtro.getModelo());
        }
        return carrosRepositorio.findAll();
    }

    public Carros criar(CarrosRequestDto cliente) {
        Carros clientePersist = this.carrosRequestDtoParaCarros(cliente);

        return carrosRepositorio.save(clientePersist);
    }

    public Carros atualizar(Long id, CarrosRequestDto cliente){
        if (carrosRepositorio.existsById(id)){
            Carros clientesPersist = this.carrosRequestDtoParaCarros(cliente);
            clientesPersist.setId(id);

            return carrosRepositorio.save(clientesPersist);
        }
        throw new RuntimeException("Carro não encontrado");
    }

    public void deletar(Long id){
        if (carrosRepositorio.existsById(id)){
            carrosRepositorio.deleteById(id);
        }
        throw new RuntimeException("Cliente não encontrado");
    }

    public Carros ListarById(Long id){
        if (carrosRepositorio.existsById(id)){
            return carrosRepositorio.findById(id).get();
        }
        throw new RuntimeException("Carro não existe");
    }

    private Carros carrosRequestDtoParaCarros (CarrosRequestDto entrada){
    Carros saida = new Carros();
    saida.setAno(entrada.getAno());
    saida.setPlaca(entrada.getPlaca());
    saida.setModelo(entrada.getModelo());
    saida.setMarca(entrada.getMarca());
    return saida;
    }
}
