package com.sifast.socle.javaee.service.tracker;

import java.time.Instant;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.sifast.socle.javaee.entities.GenericTrack;
import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.enums.EventType;
import com.sifast.socle.javaee.service.IGenericTrackService;
import com.sifast.socle.javaee.service.IUserService;

@Aspect
@Component
public class ServiceTracker {

	@Autowired
	private IUserService userService;

	@Autowired
	private IGenericTrackService genericTrackService;

	@AfterReturning(pointcut = "execution(* com.sifast.socle.javaee.service.impl.GenericService.save*(..)) && @target(com.sifast.socle.javaee.service.tracker.Trackable)", returning = "result")
	public void trackSaveMethod(JoinPoint joinPoint, Object result) {
		GenericTrack genericTrack = new GenericTrack();
		User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		genericTrack.setEventDate(Date.from(Instant.now()));
		genericTrack.setEventType(EventType.SAVE);
		genericTrack.setEntity(result.getClass().getSimpleName());
		genericTrack.setRecordValue(result.toString());
		genericTrack.setPerformedBy(new StringBuilder().append(user.getFirstName()).append(" ").append(user.getLastName()).toString());
		genericTrackService.save(genericTrack);
	}

	@AfterReturning(pointcut = "execution(* com.sifast.socle.javaee.service.impl.GenericService.update*(..)) && @target(com.sifast.socle.javaee.service.tracker.Trackable)", returning = "result")
	public void trackUpdateMethod(JoinPoint joinPoint, Object result) {
		GenericTrack genericTrack = new GenericTrack();
		User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		genericTrack.setEventDate(Date.from(Instant.now()));
		genericTrack.setEventType(EventType.UPDATE);
		genericTrack.setEntity(result.getClass().getSimpleName());
		genericTrack.setRecordValue(result.toString());
		genericTrack.setPerformedBy(new StringBuilder().append(user.getFirstName()).append(" ").append(user.getLastName()).toString());
		genericTrackService.save(genericTrack);
	}

	@Before("execution(* com.sifast.socle.javaee.service.impl.GenericService.delete*(..)) && @target(com.sifast.socle.javaee.service.tracker.Trackable)")
	public void trackDeleteMethod(JoinPoint joinPoint) {
		GenericTrack genericTrack = new GenericTrack();
		User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		genericTrack.setEventDate(Date.from(Instant.now()));
		genericTrack.setEventType(EventType.DELETE);
		genericTrack.setEntity(joinPoint.getArgs()[0].getClass().getSimpleName());
		genericTrack.setRecordValue(joinPoint.getArgs()[0].toString());
		genericTrack.setPerformedBy(new StringBuilder().append(user.getFirstName()).append(" ").append(user.getLastName()).toString());
		genericTrackService.save(genericTrack);
	}
}
