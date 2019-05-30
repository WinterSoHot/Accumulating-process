#!/bin/bash

Hello () {
    echo Hello,World!
}

Hello

Say () {
    echo Hello,$1 $2
    return 10
}

Say Dongxian Gu

ret=$?

echo Return value is $ret

