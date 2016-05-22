package com.holanda.futurice.arthurice.domain.repositories;

import com.holanda.futurice.arthurice.domain.model.Entity;

import java.util.List;

/**
* A generic repository interface.
*
* @param <T> The type of the items to keep.
*/
public interface IRepository<T extends Entity> {

    /**
     * Returns a collection with all the items in this repository.
     *
     * @return Collection with all the item kept in this repository.
     */
    List<T> all();

    /**
     * Returns a collection with all the items matching the given specification.
     *
     * @param specification The specification to use as filter.
     * @return All the items matching the given specification.
     */
    List<T> matching(final ISpecification<T> specification);

    /**
     * Returns a new IRepository including the given item. (The
     * correct behavior is up to the class
     * implementing this interface).
     *
     * @param item The item to include.
     * @return A new repository including the given item.
     */
    IRepository<T> including(final T item);

    /**
     * Returns a new IRepository excluding the given item. (The
     * correct behavior is up to the class
     * implementing this interface).
     *
     * @param item The item to exclude.
     * @return A new repository excluding the given item.
     */
    IRepository<T> excluding(final T item);

    /**
     * Returns the number of items in the repository.
     *
     * @return number of items.
     */
    int size();

    /**
     * Returns true if the given item is present in the repository.
     *
     * @param item The item to verify.
     * @return true if the item is present in the repository.
     */
    boolean includes(final T item);

    /**
     * Returns the contact with the given id, if any.
     *
     * @param id The id of the contact to return.
     * @return The contact with the given id.
     */
    T get(final int id);

    /**
     * Clears the repository.
     */
    void clear();
}
