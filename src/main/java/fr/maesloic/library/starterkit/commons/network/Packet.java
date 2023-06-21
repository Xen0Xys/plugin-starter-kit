package fr.maesloic.library.starterkit.commons.network;

import fr.maesloic.library.starterkit.commons.utils.GsonUtils;

public abstract class Packet {
    public String serialize(){
        return GsonUtils.getGson().toJson(this);
    }
}
