import mini from "@/mini";
import { instanceWithoutAuth } from "./instanceWithoutAuth";

export * from "./instance";
export * from "./instanceWithoutAuth";

const common = {
  sendLog: (eventType: string, eventName: string) => {
    mini
      .getToken()
      .then(() =>
        mini.sendCustomEventLog(
          uni.getStorageSync("CUSTOMER_COUPON_OPENID")!,
          eventType,
          eventName
        )
      )
      .catch(() => {
        mini.sendCustomEventLog(null, eventType, eventName);
      });
  },
  authSign: (accessCode: string) => {
    return new Promise<string>((resolve, reject) => {
      instanceWithoutAuth
        .get({
          url: `/auth/sign?accessCode=${accessCode}`,
        })
        .then((res) => {
          const data = res.data;
          console.log(data);
          if (data.jwt) {
            mini.sendUserLog(data.customId);
            uni.setStorageSync("CUSTOMER_COUPON_OPENID", data.customId);
            resolve(data.jwt);
          } else {
            reject(data.errCode);
          }
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  getAuthHeader: () => {
    return new Promise((resolve, reject) => {
      mini
        .getToken()
        .then((token) => resolve(token))
        .catch(() => {
          mini
            .authRequest()
            .then((accessCode) => {
              common
                .authSign(accessCode)
                .then((token) => {
                  mini
                    .setToken(token)
                    .then(() => resolve(token))
                    .catch((err) => reject(err));
                })
                .catch((err) => reject(err));
            })
            .catch((err) => reject(err));
        });
    });
  },
  defaultAuthCatch: (err: {
    data: { errCode: string };
    errMsg: string;
    errorCode: string;
  }) => {
    const errCode = err?.data?.errCode || err?.errMsg || err?.errorCode || err;
    if (errCode === "INTERNAL_SERVER_ERROR") {
      uni.showToast({
        title: "Server Error",
        icon: "error",
        duration: 1000,
      });
    } else if (errCode === "AUTHENTICATION_ERROR") {
      uni.showToast({
        title: "Server Error",
        icon: "error",
        duration: 1000,
      });
    } else if (errCode === "TOKEN_NULL") {
      uni.showToast({
        title: "Token Null",
        icon: "error",
        duration: 1000,
      });
    } else if (errCode === "CUSTOMER_NOT_AUTH") {
      uni.showToast({
        title: "No User Auth",
        icon: "error",
        duration: 1000,
      });
    } else if (errCode === "SYSTEM_ERROR") {
      uni.showToast({
        title: "System Error",
        icon: "error",
        duration: 1000,
      });
    } else {
      uni.showToast({
        title: "Unknow Error",
        icon: "error",
        duration: 1000,
      });
    }
    uni.reLaunch({
      url: "/pages/home/home",
    });
  },
  defaultRequestCatch: (err: { data: { errCode: string }; errMsg: string }) => {
    const errCode = err?.data?.errCode || err?.errMsg || err;
    if (errCode === "INTERNAL_SERVER_ERROR") {
      uni.showToast({
        title: "Server Error",
        icon: "error",
        duration: 1000,
      });
    } else if (errCode === "AUTHENTICATION_ERROR") {
      uni.showToast({
        title: "Server Error",
        icon: "error",
        duration: 1000,
      });
    } else if (errCode === "AUTH_TOKEN_EXPIRED") {
      uni.showToast({
        title: "Stale Token",
        icon: "error",
        duration: 1000,
      });
    } else {
      uni.showToast({
        title: "Unknow Error",
        icon: "error",
        duration: 1000,
      });
    }
  },
};

export default common;
