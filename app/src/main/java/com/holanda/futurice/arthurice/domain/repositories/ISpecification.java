package com.holanda.futurice.arthurice.domain.repositories;

/**
 * A generic specification interface.
 *
 * @param <T> The type of the object that satisfies the specification.
 */
public interface ISpecification<T> {

    /**
     * Returns true if the object satisfies the specification.
     *
     * @param object The object to verify.
     * @return true if the specification is satisfied by the object, or false.
     */
    boolean isSatisfiedBy(T object);

}
