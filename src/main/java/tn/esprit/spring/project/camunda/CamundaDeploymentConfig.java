package tn.esprit.spring.project.camunda;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Ce fichier permet d’automatiser le déploiement des processus BPMN.
@Configuration
public class CamundaDeploymentConfig {

    @Bean
    public Deployment deployProcess(RepositoryService repositoryService) {
        return repositoryService.createDeployment()
                .addClasspathResource("process_recrutement.bpmn") 
                .name("Processus de recrutement")
                .deploy();
    }
}
