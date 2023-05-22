import { ajax } from "@/mini";

const instanceWithoutAuth = ajax.create({
  baseURL: import.meta.env.VITE_APP_BASEAPI_URL,
});

instanceWithoutAuth.interceptors.request.use(
  (config) => {
    console.log("instanceWithoutAuth:request", config);
    if (import.meta.env.PROD) {
      config.header["env"] = "7-prod";
    }
    return config;
  },
  (error) => {
    console.log("instanceWithoutAuth:requestError", error);
    return Promise.reject(error);
  }
);

instanceWithoutAuth.interceptors.response.use(
  (response) => {
    console.log("instanceWithoutAuth:response", response);
    return response;
  },
  (error) => {
    console.log("instanceWithoutAuth:responseError", error);
    return Promise.reject(error);
  }
);

export { instanceWithoutAuth };
