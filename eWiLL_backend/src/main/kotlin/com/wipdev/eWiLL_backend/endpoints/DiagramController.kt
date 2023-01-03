package com.wipdev.eWiLL_backend.endpoints


import com.wipdev.eWiLL_backend.endpoints.payload.requests.DiagramPL
import com.wipdev.eWiLL_backend.services.CategoryService
import com.wipdev.eWiLL_backend.services.DiagramService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/diagram")
@RestController
@Tag(name = "Diagram", description = "Diagram API")
class DiagramController {

    @Autowired
    lateinit var iDiagramService: DiagramService



    @CrossOrigin
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = iDiagramService.getById(id)

    @CrossOrigin
    @PostMapping
    fun create(@RequestBody diagramPL: DiagramPL) = iDiagramService.create(diagramPL)

    @CrossOrigin
    @PutMapping
    fun update(@RequestBody diagramPL: DiagramPL) = diagramPL.id?.let { iDiagramService.update(it, diagramPL) }

    @CrossOrigin
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = iDiagramService.delete(id)


}