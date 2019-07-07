package com.capco.capcopay.common;

import javax.validation.constraints.NotNull;

public interface Serializer<Deserialized,Serialized> {
	Deserialized dump(@NotNull Deserialized data);
	Serialized load(@NotNull Serialized data);
}
