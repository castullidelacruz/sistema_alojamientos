import { apiClient} from "../api/apiClient";

export const hotelService = {
    getById(id) {
        return apiClient.hoteles.get(`/hoteles/${id}`);
    },

    getPrecio(id) {
    return apiClient.get("hoteles", `/hoteles/${id}/precio`);
  },

  getAll({ ciudad, categoria, precioMin, precioMax, 
    page = 0, size = 10, sort }) {
    const params = new URLSearchParams();

    if (ciudad) params.append("ciudad", ciudad);
    if (categoria) params.append("categoria", categoria);
    if (precioMin) params.append("precioMin", precioMin);
    if (precioMax) params.append("precioMax", precioMax);
    params.append("page", page);
    params.append("size", size);
    if (sort) params.append("sort", sort);

    return apiClient.hoteles
      .get(`/hoteles?${params.toString()}`)
      .then(res => res.data); 
  }

}