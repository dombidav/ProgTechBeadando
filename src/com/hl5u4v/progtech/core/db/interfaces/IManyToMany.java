package com.hl5u4v.progtech.core.db.interfaces;

import com.hl5u4v.progtech.core.db.Schema;
import com.hl5u4v.progtech.core.db.builders.IConditionQuery;
import com.hl5u4v.progtech.core.interfaces.IModel;
import org.jetbrains.annotations.NotNull;

public interface IManyToMany extends IModel {
    String getTableName();

    default <R extends IModel> IConditionQuery hasMany(@NotNull R relatedClass) {
        return relationGetter(this.getTableName(), relatedClass.getTableName(), false);
    }

    default <R extends IModel> IConditionQuery belongsToMany(@NotNull R relatedClass) {
        return relationGetter(relatedClass.getTableName(), this.getTableName(), true);
    }

    default IConditionQuery relationGetter(String localTable, String foreignTable, boolean reverse) throws NullPointerException {
        if (this.getId() == null) {
            throw new NullPointerException("id was not set for this model");
        }
        var connectTable = String.format("%s_%s", localTable, foreignTable);
        String localKey = String.format("%s_id", localTable.substring(0, localTable.length() - 1));
        String foreignKey = String.format("%s_id", foreignTable.substring(0, foreignTable.length() - 1));
        if (reverse) {
            var temp = localKey;
            localKey = foreignKey;
            foreignKey = temp;
        }
        return Schema.table(connectTable).select().join((!reverse ? foreignTable : localTable), foreignKey, "id").where(localKey, "=", this.getId());
    }
}
