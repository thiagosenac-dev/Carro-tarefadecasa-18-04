package br.com.senac.carros.controller;

import br.com.senac.carros.dtos.CarrosRequestDto;
import br.com.senac.carros.entidades.Carros;
import br.com.senac.carros.services.CarrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carros")
public class CarrosController {
    private CarrosService carrosService;

    public CarrosController(CarrosService carrosService) {
        this.carrosService = carrosService;
    }

    @GetMapping
    public ResponseEntity<List<Carros>> lisar(){
        return ResponseEntity.ok(carrosService.listar());
    }
    @PostMapping("/criar")
    public ResponseEntity<Carros> criar(
            @RequestBody CarrosRequestDto cliente){
        try{
            return ResponseEntity
                    .ok(carrosService.criar(cliente));
        }catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("/atualizat/{id}")
    public ResponseEntity<Carros> atualizar(
            @RequestBody CarrosRequestDto cliente,
            @PathVariable Long id){
        try{
            return ResponseEntity.ok(carrosService.atualizar(id, cliente));

        }catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public  ResponseEntity<Void> deletar (@PathVariable Long id){
        try{
            carrosService.deletar(id);
            return ResponseEntity.ok(null);
        }catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }


    }
}
