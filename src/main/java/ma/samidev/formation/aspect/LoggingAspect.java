package ma.samidev.formation.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
 * LoggingAspect est un aspect => Mise en oeuvre de la journalisation 
 * Il s'agit d'une fonctionnalité technique transverse
 */
@Aspect
public class LoggingAspect {

	/*
	 * Un advice de type before Le caractère * indique n'importe quelle séquence
	 * de caractère (modificateur, type de retour,...) (..) indique n'importe quelle signature
	 */
	@Before("beforeCustomerServiceAddCustomer()")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
	}
		
	/*
	 * Définition d'un pointcut 
	 */
	@Pointcut("execution(* ma.samidev.formation.service.CustomerService.addCustomer(..))")
	private void beforeCustomerServiceAddCustomer() {		
	}
	
	@After("execution(* ma.samidev.formation.service.CustomerService.addCustomer(..))")
	public void logAfter() {
		System.out.println("logAfter() is running!");
	}
	
	@AfterReturning(pointcut = "execution(* ma.samidev.formation.service.CustomerService.addCustomerReturnValue(..))",
			returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("logAfterReturning() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + result);
	}
	
	@AfterThrowing(pointcut = "execution(* ma.samidev.formation.service.CustomerService.addCustomerThrowException(..))",
			throwing = "error")
	public void logAfterThrowingException(JoinPoint joinPoint, Throwable error) {
		System.out.println("logAfterReturning() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Exception : " + error.getMessage());
	}
	
	@Around("execution(* ma.samidev.formation.service.CustomerService.addCustomerAround(..))")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("logAround() is running!");
		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
		System.out.println("Around before is running!");
		joinPoint.proceed(); // continue on the intercepted method
		System.out.println("Around after is running!");
	}

}
