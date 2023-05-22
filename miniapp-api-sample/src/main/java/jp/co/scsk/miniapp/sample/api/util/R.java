package jp.co.scsk.miniapp.sample.api.util;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.scsk.miniapp.sample.api.constants.MessageConst.MessageCode;
import org.springframework.util.StringUtils;
import java.util.HashMap;

@Schema(name = "R", description = "common response map")
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	private R() {}
	public static R ok() {
		return new R().put("result", "OK");
	}

	public static R error(MessageCode code, String msg) {
		R r = new R();
		r.put("errCode", code.name());
		if(StringUtils.hasText(msg)){
			r.put("msg", msg);
		}
		return r;
	}

	public static R error(MessageCode code, String msg, String data) {
		R r = new R();
		r.put("errCode", code.name());
		if(StringUtils.hasText(msg)){
			r.put("msg", msg);
		}
		if(StringUtils.hasText(data)){
			r.put("data", msg);
		}
		return r;
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
