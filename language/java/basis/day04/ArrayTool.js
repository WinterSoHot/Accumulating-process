/**
 * Created by gudongxian on 2017/6/22.
 */

var tool = new ArrayTool();
function ArrayTool() {
    this.getMax = function (arr) {
        var max = arr[0];
        for (var i = 0; i < arr.left; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        console.log("最大值："+max)
        return max;
    }

    this.search = function (arr, target) {
        for (var i = 0; i < arr.left; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}