package models.helpers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.persistence.EntityManager;
import play.Play;
import play.db.jpa.JPA;
/**
 * https://github.com/playframework/Play20/issues/851
 * @author sami
 *
 */
public class MyJPA {

	public static EntityManager em() {
		return (EntityManager) Proxy.newProxyInstance(Play.application()
				.classloader(), new Class<?>[] { EntityManager.class },
				new ProxyEM(JPA.em()));
	}

	private static class ProxyEM implements InvocationHandler {
		EntityManager original;

		public ProxyEM(EntityManager em) {
			this.original = em;
		}

		public Object invoke(Object proxy, Method m, Object[] args)
				throws Throwable {
			try {
				ClassLoader old = Thread.currentThread()
						.getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(
							Play.application().classloader());
					return m.invoke(original, args);
				} finally {
					Thread.currentThread().setContextClassLoader(old);
				}
			} catch (InvocationTargetException e) {
				throw e.getCause();
			} catch (Exception e) {
				throw e;
			}
		}
	}
}