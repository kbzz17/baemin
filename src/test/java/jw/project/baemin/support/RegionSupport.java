package jw.project.baemin.support;

import jw.project.baemin.region.domain.RegionCode;

public class RegionSupport {

    private static final String code = "1126010100";
    private static final String regionAddress = "서울특별시 중랑구 면목동";

    public static RegionCode get(Long id) {
        return RegionCode.builder()
            .code(code)
            .regionAddress(regionAddress)
            .build();
    }
}
