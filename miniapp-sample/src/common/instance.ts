import { ajax } from "@/mini";
import common from ".";

const instance = ajax.create({
  baseURL: import.meta.env.VITE_APP_BASEAPI_URL,
});

instance.interceptors.request.use(
  async (config) => {
    console.log("instance:request", config);
    if (import.meta.env.PROD) {
      config.header["env"] = "7-prod";
    }
    const token = await common.getAuthHeader().catch(common.defaultAuthCatch);
    config.header["token"] = token;
    return config;
  },
  (error) => {
    console.log("instance:requestError", error);
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (response) => {
    console.log("instance:response", response);
    return response;
  },
  (error) => {
    console.log("instance:responseError", error);
    return Promise.reject(error);
  }
);

export { instance };
