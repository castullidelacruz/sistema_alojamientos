import axios from "axios";


/**
 * Instancias base de Axios
 * Una por microservicio
 */
const hotelApi = axios.create({
  baseURL: "http://localhost:8082",
  headers: {
    "Content-Type": "application/json"
  }
});

const reservaApi = axios.create({
  baseURL: "http://localhost:8081",
  headers: {
    "Content-Type": "application/json"
  }
});

/**
 * Interceptor de respuestas
 * Manejo centralizado de errores
 */
const responseInterceptor = (response) => response;

const errorInterceptor = (error) => {
  if (error.response) {
    // El backend respondió con error (400, 404, 500, etc.)
    const message =
      error.response.data?.message ||
      error.response.data ||
      "Error en el servidor";

    return Promise.reject(new Error(message));
  }

  if (error.request) {
    // No hubo respuesta (backend caído, CORS, red)
    return Promise.reject(
      new Error("No se pudo conectar con el servidor")
    );
  }

  // Error interno de Axios
  return Promise.reject(error);
};

hotelApi.interceptors.response.use(
    responseInterceptor,
    errorInterceptor
);

reservaApi.interceptors.response.use(
    responseInterceptor,
    errorInterceptor
);

export const apiClient = {
    hoteles: {
        get: (url, config) => hotelApi.get(url, config),
        post: (url, body) => hotelApi.post(url, body),
        patch: (url, body) => hotelApi.patch(url, body),
        delete: (url) => hotelApi.delete(url)
    },

    reservas: {
        get: (url, config) => reservaApi.get(url, config),
        post: (url, body) => reservaApi.post(url, body),
        patch: (url, body) => reservaApi.patch(url, body),
        delete: (url) => reservaApi.delete(url)
    }
}