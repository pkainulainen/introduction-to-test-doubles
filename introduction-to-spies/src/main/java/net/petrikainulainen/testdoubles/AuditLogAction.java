package net.petrikainulainen.testdoubles;

/**
 * Specifies the possible actions.
 */
enum AuditLogAction {

    /**
     * A new row was inserted into the database.
     */
    CREATE,
    /**
     * An existing row was deleted from the database.
     */
    DELETE,
    /**
     * The information of an existing database row was updated.
     */
    UPDATE;
}
