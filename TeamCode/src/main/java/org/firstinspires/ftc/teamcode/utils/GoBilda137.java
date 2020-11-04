package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.configuration.DistributorInfo;
import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties;
import com.qualcomm.robotcore.hardware.configuration.annotations.MotorType;

import org.firstinspires.ftc.robotcore.external.navigation.Rotation;

@MotorType(ticksPerRev = 383.6, gearing = 13.7, maxRPM = 435, orientation = Rotation.CCW)
@DeviceProperties(xmlTag = "GoBilda137Motor", name = "GoBilda 13.7", builtIn = false)
@DistributorInfo(distributor = "GoBilda", model = "5202-0002-0014", url = "https://www.gobilda.com/5202-series-yellow-jacket-planetary-gear-motor-13-7-1-ratio-435-rpm-3-3-5v-encoder/")
public interface GoBilda137 {
}
