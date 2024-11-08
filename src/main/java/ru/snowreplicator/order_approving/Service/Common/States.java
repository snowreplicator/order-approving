package ru.snowreplicator.order_approving.Service.Common;

public enum States {
    DRAFT,          // draft order
    IN_PROGRESS,    // order in progress
    REJECTED,       // rejected order
    ACCEPTED        // approved order
}
