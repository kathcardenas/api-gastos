package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.StatusModel;
import com.example.gastos.gastos.repositories.IStatusRepository;

@Service
public class StatusService {
    @Autowired
    private IStatusRepository repository;

    /*
     * Listar los estados a partir del ID de forma descendiente
     */
    public List<StatusModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public List<StatusModel> FixedCostlist(List<Long> id){
        return this.repository.findByIdIn(id);
    }


    /*
     * Método guardar
     * Recibe el modelo
     */
    public void save(StatusModel model){
        this.repository.save(model);
    }

    /*
     * Buscar por ID
     * Recibe el id del estado
     */
    public StatusModel findById(Long id){
        Optional<StatusModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    /*
     * Eliminar un estado por su id
     * Recibe el id del estado
     */
    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
