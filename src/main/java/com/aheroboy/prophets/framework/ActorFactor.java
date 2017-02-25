package com.aheroboy.prophets.framework;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by sgyang on 2/21/17.
 */
public class ActorFactor {
    public Object createObj() throws Exception{
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("str",String.class);
        beanGenerator.addProperty("intValue",Integer.class);
        beanGenerator.addProperty("doubleValue",Double.class);
        beanGenerator.addProperty("bigdecimalValue",BigDecimal.class);
        beanGenerator.addProperty("boolValue",Boolean.class);
        beanGenerator.addProperty("LongValue",Long.class);
        Object obj = beanGenerator.create();
        Method setter = obj.getClass().getMethod("setIntValue", Integer.class);
        setter.invoke(obj,100);
        Method getter = obj.getClass().getMethod("getIntValue");
        System.out.println(getter.invoke(obj));

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return methodProxy.invoke(o,objects);
            }
        });
        Object obj1 = enhancer.create();
        getter = obj1.getClass().getMethod("getIntValue");
        System.out.println(getter.invoke(obj1));
        return obj;

    }

    class Wrapper extends HashMap<String,Object>{

        public Wrapper(int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
        }

        public Wrapper(int initialCapacity) {
            super(initialCapacity);
        }

        public Wrapper() {
            super();
        }

        public Wrapper(Map<? extends String, ?> m) {
            super(m);
        }

        @Override public int size() {
            return super.size();
        }

        @Override public boolean isEmpty() {
            return super.isEmpty();
        }

        @Override public Object get(Object key) {
            return super.get(key);
        }

        @Override public boolean containsKey(Object key) {
            return super.containsKey(key);
        }

        @Override public Object put(String key, Object value) {
            return super.put(key, value);
        }

        @Override public void putAll(Map<? extends String, ?> m) {
            super.putAll(m);
        }

        @Override public Object remove(Object key) {
            return super.remove(key);
        }

        @Override public void clear() {
            super.clear();
        }

        @Override public boolean containsValue(Object value) {
            return super.containsValue(value);
        }

        @Override public Set<String> keySet() {
            return super.keySet();
        }

        @Override public Collection<Object> values() {
            return super.values();
        }

        @Override public Set<Entry<String, Object>> entrySet() {
            return super.entrySet();
        }

        @Override public Object getOrDefault(Object key, Object defaultValue) {
            return super.getOrDefault(key, defaultValue);
        }

        @Override public Object putIfAbsent(String key, Object value) {
            return super.putIfAbsent(key, value);
        }

        @Override public boolean remove(Object key, Object value) {
            return super.remove(key, value);
        }

        @Override public boolean replace(String key, Object oldValue, Object newValue) {
            return super.replace(key, oldValue, newValue);
        }

        @Override public Object replace(String key, Object value) {
            return super.replace(key, value);
        }

        @Override public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
            return super.computeIfAbsent(key, mappingFunction);
        }

        @Override public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
            return super.computeIfPresent(key, remappingFunction);
        }

        @Override public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
            return super.compute(key, remappingFunction);
        }

        @Override public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction) {
            return super.merge(key, value, remappingFunction);
        }

        @Override public void forEach(BiConsumer<? super String, ? super Object> action) {
            super.forEach(action);
        }

        @Override public void replaceAll(BiFunction<? super String, ? super Object, ?> function) {
            super.replaceAll(function);
        }

        @Override public Object clone() {
            return super.clone();
        }
    }

    public static void main(String xargs[]) throws Exception{
        ActorFactor af = new ActorFactor();
        af.createObj();
    }
}
