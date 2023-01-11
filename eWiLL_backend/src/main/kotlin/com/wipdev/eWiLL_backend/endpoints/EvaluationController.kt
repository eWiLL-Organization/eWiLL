package com.wipdev.eWiLL_backend.endpoints

import com.wipdev.eWiLL_backend.endpoints.payload.requests.SubmissionRequestPL
import com.wipdev.eWiLL_backend.repository.DiagramRepository
import com.wipdev.eWiLL_backend.repository.TaskRepository
import com.wipdev.eWiLL_backend.services.EvaluationService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/evaluation/v2")
@RestController
@Tag(name = "Evaluation/Checker", description = "Checker API")
class EvaluationController {

    @Autowired
    lateinit var service: EvaluationService

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Autowired
    lateinit var diagramRepository: DiagramRepository

    @PostMapping("/submit")
    @ResponseBody
    fun submit(@Parameter submissionRequestPL: SubmissionRequestPL): String? {
        var task = taskRepository.findById(submissionRequestPL.taskId).get()
        var solutionModel = task.solutionModelId?.let { diagramRepository.findById(it).get() }
        val model = submissionRequestPL.diagramPL


        return service.eval(submissionRequestPL).toString()
    }




}