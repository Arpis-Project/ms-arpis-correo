package cl.arpis.correo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class MapperUtils {
	private final static ModelMapper MODEL_MAPPER;

	static {
		MODEL_MAPPER = new ModelMapper();
	}

	public static <T> T objectToObject(Object source, Class<T> destinationClass) {
		if (source == null) {
			return null;
		}
		return MODEL_MAPPER.map(source, destinationClass);
	}

	public static <T, U> List<T> listToList(List<U> sourceList, Class<T> destinationClass) {
		if (sourceList == null) {
			return new ArrayList<T>();
		}
		return sourceList.stream().map(e -> objectToObject(e, destinationClass)).collect(Collectors.toList());
	}
}
