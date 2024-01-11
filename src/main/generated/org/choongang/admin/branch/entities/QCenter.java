package org.choongang.admin.branch.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCenter is a Querydsl query type for Center
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCenter extends EntityPathBase<Center> {

    private static final long serialVersionUID = 641968081L;

    public static final QCenter center = new QCenter("center");

    public final org.choongang.commons.entities.QBaseMember _super = new org.choongang.commons.entities.QBaseMember(this);

    public final StringPath address = createString("address");

    public final StringPath addressSub = createString("addressSub");

    public final StringPath bookAvl = createString("bookAvl");

    public final NumberPath<Integer> bookBlock = createNumber("bookBlock", Integer.class);

    public final NumberPath<Integer> bookCapacity = createNumber("bookCapacity", Integer.class);

    public final BooleanPath bookHday = createBoolean("bookHday");

    public final StringPath bookNotAvl = createString("bookNotAvl");

    public final StringPath bookYoil = createString("bookYoil");

    public final NumberPath<Long> cCode = createNumber("cCode", Long.class);

    public final StringPath cName = createString("cName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath operHour = createString("operHour");

    public final StringPath telNum = createString("telNum");

    public final StringPath zonecode = createString("zonecode");

    public QCenter(String variable) {
        super(Center.class, forVariable(variable));
    }

    public QCenter(Path<? extends Center> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCenter(PathMetadata metadata) {
        super(Center.class, metadata);
    }

}

