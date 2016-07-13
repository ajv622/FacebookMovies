package edu.galileo.android.facebookmovies.lib;

/**
 * Created by ajv.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
