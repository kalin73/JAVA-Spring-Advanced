package proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheableInvocationHandler implements InvocationHandler {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();
    private final StudentService delegate;

    public CacheableInvocationHandler(StudentService studentService) {
        this.delegate = studentService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cacheable cacheableAnnotation = delegate
                .getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Cacheable.class);


        if (cacheableAnnotation != null) {
            final String cacheKey = cacheableAnnotation.name();

            if (cache.containsKey(cacheKey)) {
                return cache.get(cacheKey);
            }

            Object result = method.invoke(delegate, args);

            cache.put(cacheKey, result);

            return cache.get(cacheKey);
        }

        return method.invoke(delegate, args);
    }
}
