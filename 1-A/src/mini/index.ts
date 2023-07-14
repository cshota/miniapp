// JSSDK 1.1.0
import ajax from "uni-ajax";
export * from "uni-ajax";
export { ajax };
const mini = {
  init: () => {
    // #ifndef H5
    /* @ts-ignore */
    uni.onNativeEventReceive((event: string) => {
      if (event === "MF_RESTART_EVENT") {
        // @ts-ignore
        uni.sendNativeEvent("MF_CLEAR_TOKEN_EVENT", {}, () => {});
        uni.clearStorageSync();
        plus.runtime.restart();
      }
    });
    // #endif
  },
  callHostEvent: (
    eventName: string,
    sendData?: string,
    callback?: (returnData: string) => void
  ) => {
    // #ifndef H5
    return new Promise((reslove) => {
      // @ts-ignore
      uni.sendNativeEvent(
        "MF_DATA_EVENT",
        { event: eventName, data: sendData || "" },
        (returnData: string) => {
          callback?.(returnData);
          reslove(returnData);
        }
      );
    });
    // #endif
  },
  openMap: (queryName: string, longitude: number, latitude: number) => {
    // #ifdef H5
    if (typeof plus !== "undefined") {
      // #endif
      if (plus.os.name == "Android") {
        plus.runtime.openURL(
          `geo:${latitude},${longitude}?q=${latitude},${longitude}(${queryName})`
        );
      } else {
        plus.runtime.openURL(
          `https://maps.apple.com/place?q=${queryName}&ll=${latitude},${longitude}&spn=0.008766,0.019441`
        );
      }
      // #ifdef H5
    } else {
      console.log(
        `latitude=${latitude},longitude=${longitude},queryName=${queryName}`
      );
    }
    // #endif
  },
  sendErrorLog: (
    openCustomerId: string | null,
    errorItem1: string,
    errorItem2?: string,
    errorItem3?: string
  ) => {
    if (errorItem1) {
      // #ifdef H5
      console.error(
        "[Error Log]",
        openCustomerId || "",
        errorItem1,
        errorItem2,
        errorItem3
      );
      // #endif
      // #ifndef H5
      // @ts-ignore
      uni.sendNativeEvent("MF_SEND_ERROR_LOG", {
        openCustomerId: openCustomerId || "",
        errorItem1,
        errorItem2: errorItem2 || "",
        errorItem3: errorItem3 || "",
      });
      // #endif
    }
  },
  sendGuestLog: () => {
    // #ifdef H5
    console.log("[Guest Log]");
    // #endif
    // #ifndef H5
    // @ts-ignore
    uni.sendNativeEvent("MF_GUEST_LOG");
    // #endif
  },
  sendUserLog: (openCustomerId: string) => {
    // #ifdef H5
    console.log("[User Log]", openCustomerId || "");
    // #endif
    // #ifndef H5
    // @ts-ignore
    uni.sendNativeEvent("MF_USER_LOG", {
      openCustomerId: openCustomerId || "",
    });
    // #endif
  },
  sendCustomEventLog: (
    openCustomerId: string | null,
    eventType: string,
    eventName: string
  ) => {
    if (eventType) {
      // #ifdef H5
      console.log(
        "[Custom Event Log]",
        openCustomerId || "",
        eventType,
        eventName
      );
      // #endif
      // #ifndef H5
      // @ts-ignore
      uni.sendNativeEvent("MF_CUSTOM_EVENT_LOG", {
        uuName: openCustomerId || "",
        eventType,
        eventName,
      });
      // #endif
    }
  },
  clearToken: () => {
    return new Promise<void>((resolve, reject) => {
      // #ifdef H5
      uni.removeStorage({
        key: "__MINI_TOKEN_STORAGE",
        success: () => {
          resolve();
        },
        fail: () => {
          reject(new Error("clear token failed"));
        },
      });
      // #endif
      // #ifndef H5
      // @ts-ignore
      uni.sendNativeEvent("MF_CLEAR_TOKEN_EVENT", {}, (e) => {
        if (e?.errorInfo?.errorCode) {
          reject(e.errorInfo);
        } else {
          resolve();
        }
      });
      // #endif
    });
  },
  getToken: () => {
    return new Promise((resolve, reject) => {
      // #ifdef H5
      uni.getStorage({
        key: "__MINI_TOKEN_STORAGE",
        success: (e) => {
          if (e.data) {
            resolve(e.data);
          } else {
            reject({
              errorCode: "TOKEN_NULL",
              message: "token is null",
            });
          }
        },
        fail: () => {
          reject({
            errorCode: "TOKEN_NULL",
            message: "token is null",
          });
        },
      });
      // #endif
      // #ifndef H5
      // @ts-ignore
      uni.sendNativeEvent("MF_GET_TOKEN_EVENT", {}, (e) => {
        if (e?.errorInfo?.errorCode) {
          reject(e.errorInfo);
        } else {
          resolve(e.token);
        }
      });
      // #endif
    });
  },
  setToken: (token: string) => {
    return new Promise<void>((resolve, reject) => {
      // #ifdef H5
      if (token === "") {
        reject({
          errorCode: "TOKEN_NULL",
          message: "token is null",
        });
      } else {
        uni.setStorage({
          key: "__MINI_TOKEN_STORAGE",
          data: token,
          success: () => {
            resolve();
          },
          fail: () => {
            reject({
              errorCode: "TOKEN_NULL",
              message: "token is null",
            });
          },
        });
      }
      // #endif
      // #ifndef H5
      // @ts-ignore
      uni.sendNativeEvent("MF_SET_TOKEN_EVENT", { token }, (e) => {
        if (e?.errorInfo?.errorCode) {
          reject(e.errorInfo);
        } else {
          resolve();
        }
      });
      // #endif
    });
  },
  authRequest: (config?: { isSilent?: boolean; h5Mock?: string }) => {
    return new Promise<string>((resolve, reject) => {
      // #ifdef H5
      resolve(config?.h5Mock || "H5MOCK_HERE_FOR_TEST");
      // #endif
      // #ifndef H5
      // @ts-ignore
      uni.sendNativeEvent(
        "MF_AUTH_REQUEST_EVENT",
        {
          isSilent: config?.isSilent || false,
        },
        (e: { errorInfo: { errorCode: string }; accessCode: string }) => {
          if (e?.errorInfo?.errorCode) {
            reject(e.errorInfo);
          } else {
            resolve(e.accessCode);
          }
        }
      );
      // #endif
    });
  },
};

export default mini;
