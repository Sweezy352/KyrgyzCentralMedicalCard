package com.example.kyrgyzstancentralmedicalcard.boot;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j                  // Автоматически подключает логгер log.*
@Aspect                // Указывает, что класс является AOP аспектом
@Component             // Spring-компонент для автоматического сканирования
public class LogAspectControler {



    @Pointcut("within(@org.springframework.stereotype.Repository *) ||" +
            " within(@org.springframework.stereotype.Service *) ||" +
            " within(@org.springframework.web.bind.annotation.RestController *)")
    private void publicMethodsFromLoggingPackageController() {
    }


    /**
     * Логгер на проверку данных входящих в метод.
     * Данная проверка логгера отрабатывает перед тем как
     * отработает ключевой метод
     * @param joinPoint
     */
    @Before("publicMethodsFromLoggingPackageController()")
    public void logBefore(JoinPoint joinPoint) {
        // Логирует начало работы метода
        log.info("------------------------>>>>> Вход в метод: {}", joinPoint.getSignature().getName());
    }

    // Выполняется после успешного выполнения метода
    @AfterReturning("publicMethodsFromLoggingPackageController()")
    public void slogAfter(JoinPoint joinPoint) {
        // Логирует выход из метода
        log.info("----------------->>>>>    Выход из метода: {}", joinPoint.getSignature().getName());
    }

    // Выполняется, если метод контроллера выбрасывает исключение
    @AfterThrowing(pointcut = "publicMethodsFromLoggingPackageController()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        // Логирует ошибку и её сообщение
        log.error("--------------------------->>>>>>>  Ошибка в методе: {} — {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}