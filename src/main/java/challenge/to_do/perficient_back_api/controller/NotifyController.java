package challenge.to_do.perficient_back_api.controller;

import challenge.to_do.perficient_back_api.repository.model.Notify;
import challenge.to_do.perficient_back_api.service.impl.TaskNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private TaskNotificationService service;



    @GetMapping("/checkAndNotify")
    public void checkAndNotify() {
        service.checkAndNotifyPendingTasks();
    }

}
