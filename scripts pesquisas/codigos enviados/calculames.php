<?php
/* Esta função calcula o próximo mês, prevendo os dia final do mês seguinte */

$createdDate = new DateTime("2015-01-30");
$next = sameDateNextMonth($createdDate, $createdDate);
echo $next->format("Y-m-d");

function sameDateNextMonth(DateTime $createdDate, DateTime $currentDate) {
    $addMon = clone $currentDate;
    $addMon->add(new DateInterval("P1M"));

    $nextMon = clone $currentDate;
    $nextMon->modify("last day of next month");

    if ($addMon->format("n") == $nextMon->format("n")) {
        $recurDay = $createdDate->format("j");
        $daysInMon = $addMon->format("t");
        $currentDay = $currentDate->format("j");
        if ($recurDay > $currentDay && $recurDay <= $daysInMon) {
            $addMon->setDate($addMon->format("Y"), $addMon->format("n"), $recurDay);
        }
        return $addMon;
    } else {
        return $nextMon;
    }
}
/*calcula mes*/
?>
