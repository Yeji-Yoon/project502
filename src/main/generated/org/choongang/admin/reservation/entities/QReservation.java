package org.choongang.admin.reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 594069756L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final org.choongang.commons.entities.QBase _super = new org.choongang.commons.entities.QBase(this);

    public final NumberPath<Long> bookCode = createNumber("bookCode", Long.class);

    public final DateTimePath<java.time.LocalDateTime> bookDateTime = createDateTime("bookDateTime", java.time.LocalDateTime.class);

    public final EnumPath<org.choongang.admin.reservation.constants.DonationType> bookType = createEnum("bookType", org.choongang.admin.reservation.constants.DonationType.class);

    public final org.choongang.admin.branch.entities.QCenter center;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath donnerTel = createString("donnerTel");

    public final org.choongang.member.entities.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.center = inits.isInitialized("center") ? new org.choongang.admin.branch.entities.QCenter(forProperty("center")) : null;
        this.member = inits.isInitialized("member") ? new org.choongang.member.entities.QMember(forProperty("member")) : null;
    }

}

