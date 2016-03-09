// Solution to https://www.codeeval.com/open_challenges/215/

package main

import (
	"bufio"
	"fmt"
	"os"
)

func countMatching(a string, b string) int {
	ra := []rune(a)
	rb := []rune(b)
	count := 1
	for i := 0; i < len(ra); i++ {
		r1 := ra[i]
		r2 := rb[i]
		if r1 != r2 && r1 != '*' && r2 != '*' {
			return 0
		}
		if r1 == '*' && r2 == '*' {
			count *= 2
		}
	}
	return count
}

func main() {
	if len(os.Args) != 2 {
		os.Exit(1)
	}

	file, err := os.Open(os.Args[1])
	if err != nil {
		os.Exit(1)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		a := line[0 : len(line)/2]
		b := line[len(line)/2:]
		fmt.Println(countMatching(a, b))
	}

}
