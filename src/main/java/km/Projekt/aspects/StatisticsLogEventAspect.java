package km.Projekt.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticsLogEventAspect {

    @Before("execution(* km.Projekt.controllers.StatisticsController.*(..))")
    public void logStatisticsCallMethod(JoinPoint joinPoint) {
        System.out.println("Wywołana metoda: " + joinPoint.getSignature());
    }

    @AfterReturning(
            pointcut = "execution(* km.Projekt.controllers.StatisticsController.*(..))",
            returning = "result"
    )
    public void logStatisticExecuteMethod(JoinPoint joinPoint, Object result) {
        System.out.println("Prawidłowo wykonano metodę " + joinPoint.getSignature() + "; wynik: " + result);
    }
}
