import { apiClient} from "../api/apiClient";

export const reservaService = {
    getById(id) {
        return apiClient.get("reservas", `/reserva/${id}`);
    },

    create(reserva) {
        return apiClient.post("reservas", "/reserva/", reserva);
    },

    delete(id) {
        return apiClient.delete("reservas", `/reserva/${id}`);
    },

    cambiarEstado(id, estado) {
        return apiClient.patch(
        "reservas",
        `/reserva/${id}/estado`,
        estado
        );
    },

    actualizarFechas(id, inicio, fin) {
        const params = new URLSearchParams({
        inicio,
        fin
        });

        return apiClient.patch(
        "reservas",
        `/reserva/${id}/fechas?${params.toString()}`
        );
    }
}