package tn.esprit.spring.project.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.project.models.Notification;
import tn.esprit.spring.project.service.NotificationService;

import java.util.Date;

//Ce Service permet de gérer la notification des candidats via une tâche de service dans le BPMN

@Service("camundaNotificationService")
public class CamundaNotificationService implements JavaDelegate {

    @Autowired
    private NotificationService notificationService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String message = "Cher candidat, votre candidature a été mise à jour.";
        Long destinationId = (Long) execution.getVariable("destinationId"); // ID du candidat

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDateEnvoi(new Date());
        notification.setDestinationId(destinationId);

        // Sauvegarde de la notification
        notificationService.envoyerNotification(notification);

        // Log pour voir si ça fonctionne
        System.out.println("Notification envoyée au candidat ID : " + destinationId);
    }
}
